import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDemandeInfo, DemandeInfo } from 'app/shared/model/demande-info.model';
import { DemandeInfoService } from './demande-info.service';
import { IDemande } from 'app/shared/model/demande.model';
import { DemandeService } from 'app/entities/demande/demande.service';

@Component({
  selector: 'jhi-demande-info-update',
  templateUrl: './demande-info-update.component.html',
})
export class DemandeInfoUpdateComponent implements OnInit {
  isSaving = false;
  demandes: IDemande[] = [];
  dateDemandeDp: any;
  dateReponseDp: any;

  editForm = this.fb.group({
    id: [],
    nordre: [],
    infoDemande: [],
    reponse: [],
    dateDemande: [],
    dateReponse: [],
    demande: [],
  });

  constructor(
    protected demandeInfoService: DemandeInfoService,
    protected demandeService: DemandeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandeInfo }) => {
      this.updateForm(demandeInfo);

      this.demandeService.query().subscribe((res: HttpResponse<IDemande[]>) => (this.demandes = res.body || []));
    });
  }

  updateForm(demandeInfo: IDemandeInfo): void {
    this.editForm.patchValue({
      id: demandeInfo.id,
      nordre: demandeInfo.nordre,
      infoDemande: demandeInfo.infoDemande,
      reponse: demandeInfo.reponse,
      dateDemande: demandeInfo.dateDemande,
      dateReponse: demandeInfo.dateReponse,
      demande: demandeInfo.demande,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demandeInfo = this.createFromForm();
    if (demandeInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeInfoService.update(demandeInfo));
    } else {
      this.subscribeToSaveResponse(this.demandeInfoService.create(demandeInfo));
    }
  }

  private createFromForm(): IDemandeInfo {
    return {
      ...new DemandeInfo(),
      id: this.editForm.get(['id'])!.value,
      nordre: this.editForm.get(['nordre'])!.value,
      infoDemande: this.editForm.get(['infoDemande'])!.value,
      reponse: this.editForm.get(['reponse'])!.value,
      dateDemande: this.editForm.get(['dateDemande'])!.value,
      dateReponse: this.editForm.get(['dateReponse'])!.value,
      demande: this.editForm.get(['demande'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeInfo>>): void {
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
