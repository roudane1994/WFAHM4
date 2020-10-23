import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from './fournisseur.service';
import { FournisseurDeleteDialogComponent } from './fournisseur-delete-dialog.component';

@Component({
  selector: 'jhi-fournisseur',
  templateUrl: './fournisseur.component.html',
})
export class FournisseurComponent implements OnInit, OnDestroy {
  fournisseurs?: IFournisseur[];
  eventSubscriber?: Subscription;

  constructor(
    protected fournisseurService: FournisseurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fournisseurService.query().subscribe((res: HttpResponse<IFournisseur[]>) => (this.fournisseurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFournisseurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFournisseur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFournisseurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('fournisseurListModification', () => this.loadAll());
  }

  delete(fournisseur: IFournisseur): void {
    const modalRef = this.modalService.open(FournisseurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fournisseur = fournisseur;
  }
}
