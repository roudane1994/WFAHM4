import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObjet } from 'app/shared/model/objet.model';

@Component({
  selector: 'jhi-objet-detail',
  templateUrl: './objet-detail.component.html',
})
export class ObjetDetailComponent implements OnInit {
  objet: IObjet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objet }) => (this.objet = objet));
  }

  previousState(): void {
    window.history.back();
  }
}
