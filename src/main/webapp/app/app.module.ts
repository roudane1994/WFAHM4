import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Wfahm4SharedModule } from 'app/shared/shared.module';
import { Wfahm4CoreModule } from 'app/core/core.module';
import { Wfahm4AppRoutingModule } from './app-routing.module';
import { Wfahm4HomeModule } from './home/home.module';
import { Wfahm4EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Wfahm4SharedModule,
    Wfahm4CoreModule,
    Wfahm4HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Wfahm4EntityModule,
    Wfahm4AppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Wfahm4AppModule {}
