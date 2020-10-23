import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPieceJoindre, PieceJoindre } from 'app/shared/model/piece-joindre.model';
import { PieceJoindreService } from './piece-joindre.service';
import { PieceJoindreComponent } from './piece-joindre.component';
import { PieceJoindreDetailComponent } from './piece-joindre-detail.component';
import { PieceJoindreUpdateComponent } from './piece-joindre-update.component';

@Injectable({ providedIn: 'root' })
export class PieceJoindreResolve implements Resolve<IPieceJoindre> {
  constructor(private service: PieceJoindreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPieceJoindre> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pieceJoindre: HttpResponse<PieceJoindre>) => {
          if (pieceJoindre.body) {
            return of(pieceJoindre.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PieceJoindre());
  }
}

export const pieceJoindreRoute: Routes = [
  {
    path: '',
    component: PieceJoindreComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.pieceJoindre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PieceJoindreDetailComponent,
    resolve: {
      pieceJoindre: PieceJoindreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.pieceJoindre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PieceJoindreUpdateComponent,
    resolve: {
      pieceJoindre: PieceJoindreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.pieceJoindre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PieceJoindreUpdateComponent,
    resolve: {
      pieceJoindre: PieceJoindreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.pieceJoindre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
