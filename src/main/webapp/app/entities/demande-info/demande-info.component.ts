import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDemandeInfo } from 'app/shared/model/demande-info.model';
import { DemandeInfoService } from './demande-info.service';
import { DemandeInfoDeleteDialogComponent } from './demande-info-delete-dialog.component';

@Component({
  selector: 'jhi-demande-info',
  templateUrl: './demande-info.component.html',
})
export class DemandeInfoComponent implements OnInit, OnDestroy {
  demandeInfos?: IDemandeInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected demandeInfoService: DemandeInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.demandeInfoService.query().subscribe((res: HttpResponse<IDemandeInfo[]>) => (this.demandeInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDemandeInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDemandeInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDemandeInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('demandeInfoListModification', () => this.loadAll());
  }

  delete(demandeInfo: IDemandeInfo): void {
    const modalRef = this.modalService.open(DemandeInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.demandeInfo = demandeInfo;
  }
}
