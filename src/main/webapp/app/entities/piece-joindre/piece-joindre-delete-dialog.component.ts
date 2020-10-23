import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPieceJoindre } from 'app/shared/model/piece-joindre.model';
import { PieceJoindreService } from './piece-joindre.service';

@Component({
  templateUrl: './piece-joindre-delete-dialog.component.html',
})
export class PieceJoindreDeleteDialogComponent {
  pieceJoindre?: IPieceJoindre;

  constructor(
    protected pieceJoindreService: PieceJoindreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pieceJoindreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pieceJoindreListModification');
      this.activeModal.close();
    });
  }
}
