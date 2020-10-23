import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPieceJoindre } from 'app/shared/model/piece-joindre.model';

@Component({
  selector: 'jhi-piece-joindre-detail',
  templateUrl: './piece-joindre-detail.component.html',
})
export class PieceJoindreDetailComponent implements OnInit {
  pieceJoindre: IPieceJoindre | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pieceJoindre }) => (this.pieceJoindre = pieceJoindre));
  }

  previousState(): void {
    window.history.back();
  }
}
