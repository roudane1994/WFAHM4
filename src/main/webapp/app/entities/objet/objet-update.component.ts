import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IObjet, Objet } from 'app/shared/model/objet.model';
import { ObjetService } from './objet.service';

@Component({
  selector: 'jhi-objet-update',
  templateUrl: './objet-update.component.html',
})
export class ObjetUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    objet: [],
  });

  constructor(protected objetService: ObjetService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objet }) => {
      this.updateForm(objet);
    });
  }

  updateForm(objet: IObjet): void {
    this.editForm.patchValue({
      id: objet.id,
      objet: objet.objet,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const objet = this.createFromForm();
    if (objet.id !== undefined) {
      this.subscribeToSaveResponse(this.objetService.update(objet));
    } else {
      this.subscribeToSaveResponse(this.objetService.create(objet));
    }
  }

  private createFromForm(): IObjet {
    return {
      ...new Objet(),
      id: this.editForm.get(['id'])!.value,
      objet: this.editForm.get(['objet'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObjet>>): void {
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
}
