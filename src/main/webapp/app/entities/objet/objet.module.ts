import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wfahm4SharedModule } from 'app/shared/shared.module';
import { ObjetComponent } from './objet.component';
import { ObjetDetailComponent } from './objet-detail.component';
import { ObjetUpdateComponent } from './objet-update.component';
import { ObjetDeleteDialogComponent } from './objet-delete-dialog.component';
import { objetRoute } from './objet.route';

@NgModule({
  imports: [Wfahm4SharedModule, RouterModule.forChild(objetRoute)],
  declarations: [ObjetComponent, ObjetDetailComponent, ObjetUpdateComponent, ObjetDeleteDialogComponent],
  entryComponents: [ObjetDeleteDialogComponent],
})
export class Wfahm4ObjetModule {}
