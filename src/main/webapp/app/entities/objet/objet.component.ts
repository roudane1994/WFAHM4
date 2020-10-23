import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IObjet } from 'app/shared/model/objet.model';
import { ObjetService } from './objet.service';
import { ObjetDeleteDialogComponent } from './objet-delete-dialog.component';

@Component({
  selector: 'jhi-objet',
  templateUrl: './objet.component.html',
})
export class ObjetComponent implements OnInit, OnDestroy {
  objets?: IObjet[];
  eventSubscriber?: Subscription;

  constructor(protected objetService: ObjetService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.objetService.query().subscribe((res: HttpResponse<IObjet[]>) => (this.objets = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInObjets();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IObjet): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInObjets(): void {
    this.eventSubscriber = this.eventManager.subscribe('objetListModification', () => this.loadAll());
  }

  delete(objet: IObjet): void {
    const modalRef = this.modalService.open(ObjetDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.objet = objet;
  }
}
