import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMagasin, Magasin } from 'app/shared/model/magasin.model';
import { MagasinService } from './magasin.service';
import { IDemande } from 'app/shared/model/demande.model';
import { DemandeService } from 'app/entities/demande/demande.service';
import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';
import { MagasinUtilisateurService } from 'app/entities/magasin-utilisateur/magasin-utilisateur.service';

type SelectableEntity = IMagasinUtilisateur | IDemande;

@Component({
  selector: 'jhi-magasin-update',
  templateUrl: './magasin-update.component.html',
})
export class MagasinUpdateComponent implements OnInit {
  isSaving = false;
  magasinutilisateurs: IMagasinUtilisateur[] = [];
  demandes: IDemande[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
    rtr: [],
    magasinUtilisateurs: [],
    demandes: [],
  });

  constructor(
    protected magasinService: MagasinService,
    protected demandeService: DemandeService,
    protected magasinUtilisateurService: MagasinUtilisateurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ magasin }) => {
      this.updateForm(magasin);

      this.magasinUtilisateurService
        .query()
        .subscribe((res: HttpResponse<IMagasinUtilisateur[]>) => (this.magasinutilisateurs = res.body || []));

      this.demandeService.query().subscribe((res: HttpResponse<IDemande[]>) => (this.demandes = res.body || []));
    });
  }

  updateForm(magasin: IMagasin): void {
    this.editForm.patchValue({
      id: magasin.id,
      code: magasin.code,
      libelle: magasin.libelle,
      rtr: magasin.rtr,
      magasinUtilisateurs: magasin.magasinUtilisateurs,
      demandes: magasin.demandes,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const magasin = this.createFromForm();
    if (magasin.id !== undefined) {
      this.subscribeToSaveResponse(this.magasinService.update(magasin));
    } else {
      this.subscribeToSaveResponse(this.magasinService.create(magasin));
    }
  }

  private createFromForm(): IMagasin {
    return {
      ...new Magasin(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      rtr: this.editForm.get(['rtr'])!.value,
      magasinUtilisateurs: this.editForm.get(['magasinUtilisateurs'])!.value,
      demandes: this.editForm.get(['demandes'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMagasin>>): void {
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
