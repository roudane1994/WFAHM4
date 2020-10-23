import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPieceJoindre } from 'app/shared/model/piece-joindre.model';
import { PieceJoindreService } from './piece-joindre.service';
import { PieceJoindreDeleteDialogComponent } from './piece-joindre-delete-dialog.component';

@Component({
  selector: 'jhi-piece-joindre',
  templateUrl: './piece-joindre.component.html',
})
export class PieceJoindreComponent implements OnInit, OnDestroy {
  pieceJoindres?: IPieceJoindre[];
  eventSubscriber?: Subscription;

  constructor(
    protected pieceJoindreService: PieceJoindreService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.pieceJoindreService.query().subscribe((res: HttpResponse<IPieceJoindre[]>) => (this.pieceJoindres = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPieceJoindres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPieceJoindre): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPieceJoindres(): void {
    this.eventSubscriber = this.eventManager.subscribe('pieceJoindreListModification', () => this.loadAll());
  }

  delete(pieceJoindre: IPieceJoindre): void {
    const modalRef = this.modalService.open(PieceJoindreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pieceJoindre = pieceJoindre;
  }
}
