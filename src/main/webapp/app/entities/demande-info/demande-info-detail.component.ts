import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemandeInfo } from 'app/shared/model/demande-info.model';

@Component({
  selector: 'jhi-demande-info-detail',
  templateUrl: './demande-info-detail.component.html',
})
export class DemandeInfoDetailComponent implements OnInit {
  demandeInfo: IDemandeInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandeInfo }) => (this.demandeInfo = demandeInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
