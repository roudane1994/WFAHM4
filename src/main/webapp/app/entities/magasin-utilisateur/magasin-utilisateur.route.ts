import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMagasinUtilisateur, MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';
import { MagasinUtilisateurService } from './magasin-utilisateur.service';
import { MagasinUtilisateurComponent } from './magasin-utilisateur.component';
import { MagasinUtilisateurDetailComponent } from './magasin-utilisateur-detail.component';
import { MagasinUtilisateurUpdateComponent } from './magasin-utilisateur-update.component';

@Injectable({ providedIn: 'root' })
export class MagasinUtilisateurResolve implements Resolve<IMagasinUtilisateur> {
  constructor(private service: MagasinUtilisateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMagasinUtilisateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((magasinUtilisateur: HttpResponse<MagasinUtilisateur>) => {
          if (magasinUtilisateur.body) {
            return of(magasinUtilisateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MagasinUtilisateur());
  }
}

export const magasinUtilisateurRoute: Routes = [
  {
    path: '',
    component: MagasinUtilisateurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.magasinUtilisateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MagasinUtilisateurDetailComponent,
    resolve: {
      magasinUtilisateur: MagasinUtilisateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.magasinUtilisateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MagasinUtilisateurUpdateComponent,
    resolve: {
      magasinUtilisateur: MagasinUtilisateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.magasinUtilisateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MagasinUtilisateurUpdateComponent,
    resolve: {
      magasinUtilisateur: MagasinUtilisateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wfahm4App.magasinUtilisateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
