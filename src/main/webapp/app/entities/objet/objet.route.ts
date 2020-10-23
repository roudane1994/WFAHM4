import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IObjet, Objet } from 'app/shared/model/objet.model';
import { ObjetService } from './objet.service';
import { ObjetComponent } from './objet.component';
import { ObjetDetailComponent } from './objet-detail.component';
import { ObjetUpdateComponent } from './objet-update.component';

@Injectable({ providedIn: 'root' })
export class ObjetResolve implements Resolve<IObjet> {
  constructor(private service: ObjetService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObjet> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((objet: HttpResponse<Objet>) => {
          if (objet.body) {
            return of(objet.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Objet());
  }
}

export const objetRoute: Routes = [
  {
    path: '',
    component: ObjetComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.objet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ObjetDetailComponent,
    resolve: {
      objet: ObjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.objet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ObjetUpdateComponent,
    resolve: {
      objet: ObjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.objet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ObjetUpdateComponent,
    resolve: {
      objet: ObjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.objet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
