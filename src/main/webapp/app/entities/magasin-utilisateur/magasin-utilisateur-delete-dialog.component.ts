import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';
import { MagasinUtilisateurService } from './magasin-utilisateur.service';

@Component({
  templateUrl: './magasin-utilisateur-delete-dialog.component.html',
})
export class MagasinUtilisateurDeleteDialogComponent {
  magasinUtilisateur?: IMagasinUtilisateur;

  constructor(
    protected magasinUtilisateurService: MagasinUtilisateurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.magasinUtilisateurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('magasinUtilisateurListModification');
      this.activeModal.close();
    });
  }
}
