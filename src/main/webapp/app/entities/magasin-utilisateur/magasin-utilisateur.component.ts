import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';
import { MagasinUtilisateurService } from './magasin-utilisateur.service';
import { MagasinUtilisateurDeleteDialogComponent } from './magasin-utilisateur-delete-dialog.component';

@Component({
  selector: 'jhi-magasin-utilisateur',
  templateUrl: './magasin-utilisateur.component.html',
})
export class MagasinUtilisateurComponent implements OnInit, OnDestroy {
  magasinUtilisateurs?: IMagasinUtilisateur[];
  eventSubscriber?: Subscription;

  constructor(
    protected magasinUtilisateurService: MagasinUtilisateurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.magasinUtilisateurService
      .query()
      .subscribe((res: HttpResponse<IMagasinUtilisateur[]>) => (this.magasinUtilisateurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMagasinUtilisateurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMagasinUtilisateur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMagasinUtilisateurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('magasinUtilisateurListModification', () => this.loadAll());
  }

  delete(magasinUtilisateur: IMagasinUtilisateur): void {
    const modalRef = this.modalService.open(MagasinUtilisateurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.magasinUtilisateur = magasinUtilisateur;
  }
}
