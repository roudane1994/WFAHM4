import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMagasinUtilisateur, MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';
import { MagasinUtilisateurService } from './magasin-utilisateur.service';
import { IMagasin } from 'app/shared/model/magasin.model';
import { MagasinService } from 'app/entities/magasin/magasin.service';

@Component({
  selector: 'jhi-magasin-utilisateur-update',
  templateUrl: './magasin-utilisateur-update.component.html',
})
export class MagasinUtilisateurUpdateComponent implements OnInit {
  isSaving = false;
  magasins: IMagasin[] = [];

  editForm = this.fb.group({
    id: [],
    nordre: [],
    utilisateur: [],
    magasin: [],
  });

  constructor(
    protected magasinUtilisateurService: MagasinUtilisateurService,
    protected magasinService: MagasinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ magasinUtilisateur }) => {
      this.updateForm(magasinUtilisateur);

      this.magasinService.query().subscribe((res: HttpResponse<IMagasin[]>) => (this.magasins = res.body || []));
    });
  }

  updateForm(magasinUtilisateur: IMagasinUtilisateur): void {
    this.editForm.patchValue({
      id: magasinUtilisateur.id,
      nordre: magasinUtilisateur.nordre,
      utilisateur: magasinUtilisateur.utilisateur,
      magasin: magasinUtilisateur.magasin,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const magasinUtilisateur = this.createFromForm();
    if (magasinUtilisateur.id !== undefined) {
      this.subscribeToSaveResponse(this.magasinUtilisateurService.update(magasinUtilisateur));
    } else {
      this.subscribeToSaveResponse(this.magasinUtilisateurService.create(magasinUtilisateur));
    }
  }

  private createFromForm(): IMagasinUtilisateur {
    return {
      ...new MagasinUtilisateur(),
      id: this.editForm.get(['id'])!.value,
      nordre: this.editForm.get(['nordre'])!.value,
      utilisateur: this.editForm.get(['utilisateur'])!.value,
      magasin: this.editForm.get(['magasin'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMagasinUtilisateur>>): void {
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

  trackById(index: number, item: IMagasin): any {
    return item.id;
  }
}
