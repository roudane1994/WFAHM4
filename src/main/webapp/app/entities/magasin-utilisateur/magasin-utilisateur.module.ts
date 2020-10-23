import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wfahm4SharedModule } from 'app/shared/shared.module';
import { MagasinUtilisateurComponent } from './magasin-utilisateur.component';
import { MagasinUtilisateurDetailComponent } from './magasin-utilisateur-detail.component';
import { MagasinUtilisateurUpdateComponent } from './magasin-utilisateur-update.component';
import { MagasinUtilisateurDeleteDialogComponent } from './magasin-utilisateur-delete-dialog.component';
import { magasinUtilisateurRoute } from './magasin-utilisateur.route';

@NgModule({
  imports: [Wfahm4SharedModule, RouterModule.forChild(magasinUtilisateurRoute)],
  declarations: [
    MagasinUtilisateurComponent,
    MagasinUtilisateurDetailComponent,
    MagasinUtilisateurUpdateComponent,
    MagasinUtilisateurDeleteDialogComponent,
  ],
  entryComponents: [MagasinUtilisateurDeleteDialogComponent],
})
export class Wfahm4MagasinUtilisateurModule {}
