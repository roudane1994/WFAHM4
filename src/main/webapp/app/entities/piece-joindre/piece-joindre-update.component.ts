import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPieceJoindre, PieceJoindre } from 'app/shared/model/piece-joindre.model';
import { PieceJoindreService } from './piece-joindre.service';
import { IDemande } from 'app/shared/model/demande.model';
import { DemandeService } from 'app/entities/demande/demande.service';

@Component({
  selector: 'jhi-piece-joindre-update',
  templateUrl: './piece-joindre-update.component.html',
})
export class PieceJoindreUpdateComponent implements OnInit {
  isSaving = false;
  demandes: IDemande[] = [];

  editForm = this.fb.group({
    id: [],
    nordre: [],
    name: [],
    url: [],
    demande: [],
  });

  constructor(
    protected pieceJoindreService: PieceJoindreService,
    protected demandeService: DemandeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pieceJoindre }) => {
      this.updateForm(pieceJoindre);

      this.demandeService.query().subscribe((res: HttpResponse<IDemande[]>) => (this.demandes = res.body || []));
    });
  }

  updateForm(pieceJoindre: IPieceJoindre): void {
    this.editForm.patchValue({
      id: pieceJoindre.id,
      nordre: pieceJoindre.nordre,
      name: pieceJoindre.name,
      url: pieceJoindre.url,
      demande: pieceJoindre.demande,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pieceJoindre = this.createFromForm();
    if (pieceJoindre.id !== undefined) {
      this.subscribeToSaveResponse(this.pieceJoindreService.update(pieceJoindre));
    } else {
      this.subscribeToSaveResponse(this.pieceJoindreService.create(pieceJoindre));
    }
  }

  private createFromForm(): IPieceJoindre {
    return {
      ...new PieceJoindre(),
      id: this.editForm.get(['id'])!.value,
      nordre: this.editForm.get(['nordre'])!.value,
      name: this.editForm.get(['name'])!.value,
      url: this.editForm.get(['url'])!.value,
      demande: this.editForm.get(['demande'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPieceJoindre>>): void {
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

  trackById(index: number, item: IDemande): any {
    return item.id;
  }
}
