import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDemande, Demande } from 'app/shared/model/demande.model';
import { DemandeService } from './demande.service';
import { IMagasin } from 'app/shared/model/magasin.model';
import { MagasinService } from 'app/entities/magasin/magasin.service';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from 'app/entities/fournisseur/fournisseur.service';

type SelectableEntity = IMagasin | IFournisseur;

@Component({
  selector: 'jhi-demande-update',
  templateUrl: './demande-update.component.html',
})
export class DemandeUpdateComponent implements OnInit {
  isSaving = false;
  magasins: IMagasin[] = [];
  fournisseurs: IFournisseur[] = [];
  dateBesionDp: any;
  dateCreationDp: any;
  dateAffictationDp: any;
  dateCloutureDp: any;

  editForm = this.fb.group({
    id: [],
    numero: [],
    rtr: [],
    rtrLibelle: [],
    objet: [],
    description: [],
    dateBesion: [],
    dateCreation: [],
    createur: [],
    budget: [],
    affectationLibelle: [],
    dateAffictation: [],
    moreInformation: [],
    etat: [],
    messageValidation: [],
    messageClouture: [],
    dateClouture: [],
    meilleurPrixMagasin: [],
    prixNegocie: [],
    magasin: [],
    fournisseurMagasin: [],
    fournisseurFinal: [],
  });

  constructor(
    protected demandeService: DemandeService,
    protected magasinService: MagasinService,
    protected fournisseurService: FournisseurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demande }) => {
      this.updateForm(demande);

      this.magasinService.query().subscribe((res: HttpResponse<IMagasin[]>) => (this.magasins = res.body || []));

      this.fournisseurService.query().subscribe((res: HttpResponse<IFournisseur[]>) => (this.fournisseurs = res.body || []));
    });
  }

  updateForm(demande: IDemande): void {
    this.editForm.patchValue({
      id: demande.id,
      numero: demande.numero,
      rtr: demande.rtr,
      rtrLibelle: demande.rtrLibelle,
      objet: demande.objet,
      description: demande.description,
      dateBesion: demande.dateBesion,
      dateCreation: demande.dateCreation,
      createur: demande.createur,
      budget: demande.budget,
      affectationLibelle: demande.affectationLibelle,
      dateAffictation: demande.dateAffictation,
      moreInformation: demande.moreInformation,
      etat: demande.etat,
      messageValidation: demande.messageValidation,
      messageClouture: demande.messageClouture,
      dateClouture: demande.dateClouture,
      meilleurPrixMagasin: demande.meilleurPrixMagasin,
      prixNegocie: demande.prixNegocie,
      magasin: demande.magasin,
      fournisseurMagasin: demande.fournisseurMagasin,
      fournisseurFinal: demande.fournisseurFinal,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demande = this.createFromForm();
    if (demande.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeService.update(demande));
    } else {
      this.subscribeToSaveResponse(this.demandeService.create(demande));
    }
  }

  private createFromForm(): IDemande {
    return {
      ...new Demande(),
      id: this.editForm.get(['id'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      rtr: this.editForm.get(['rtr'])!.value,
      rtrLibelle: this.editForm.get(['rtrLibelle'])!.value,
      objet: this.editForm.get(['objet'])!.value,
      description: this.editForm.get(['description'])!.value,
      dateBesion: this.editForm.get(['dateBesion'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      createur: this.editForm.get(['createur'])!.value,
      budget: this.editForm.get(['budget'])!.value,
      affectationLibelle: this.editForm.get(['affectationLibelle'])!.value,
      dateAffictation: this.editForm.get(['dateAffictation'])!.value,
      moreInformation: this.editForm.get(['moreInformation'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      messageValidation: this.editForm.get(['messageValidation'])!.value,
      messageClouture: this.editForm.get(['messageClouture'])!.value,
      dateClouture: this.editForm.get(['dateClouture'])!.value,
      meilleurPrixMagasin: this.editForm.get(['meilleurPrixMagasin'])!.value,
      prixNegocie: this.editForm.get(['prixNegocie'])!.value,
      magasin: this.editForm.get(['magasin'])!.value,
      fournisseurMagasin: this.editForm.get(['fournisseurMagasin'])!.value,
      fournisseurFinal: this.editForm.get(['fournisseurFinal'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemande>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
