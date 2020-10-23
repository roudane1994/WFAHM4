import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wfahm4SharedModule } from 'app/shared/shared.module';
import { MagasinComponent } from './magasin.component';
import { MagasinDetailComponent } from './magasin-detail.component';
import { MagasinUpdateComponent } from './magasin-update.component';
import { MagasinDeleteDialogComponent } from './magasin-delete-dialog.component';
import { magasinRoute } from './magasin.route';

@NgModule({
  imports: [Wfahm4SharedModule, RouterModule.forChild(magasinRoute)],
  declarations: [MagasinComponent, MagasinDetailComponent, MagasinUpdateComponent, MagasinDeleteDialogComponent],
  entryComponents: [MagasinDeleteDialogComponent],
})
export class Wfahm4MagasinModule {}
