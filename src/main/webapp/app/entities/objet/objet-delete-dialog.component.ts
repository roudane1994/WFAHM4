import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObjet } from 'app/shared/model/objet.model';
import { ObjetService } from './objet.service';

@Component({
  templateUrl: './objet-delete-dialog.component.html',
})
export class ObjetDeleteDialogComponent {
  objet?: IObjet;

  constructor(protected objetService: ObjetService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.objetService.delete(id).subscribe(() => {
      this.eventManager.broadcast('objetListModification');
      this.activeModal.close();
    });
  }
}
