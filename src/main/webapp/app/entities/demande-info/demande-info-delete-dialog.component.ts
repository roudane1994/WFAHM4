import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandeInfo } from 'app/shared/model/demande-info.model';
import { DemandeInfoService } from './demande-info.service';

@Component({
  templateUrl: './demande-info-delete-dialog.component.html',
})
export class DemandeInfoDeleteDialogComponent {
  demandeInfo?: IDemandeInfo;

  constructor(
    protected demandeInfoService: DemandeInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.demandeInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('demandeInfoListModification');
      this.activeModal.close();
    });
  }
}
