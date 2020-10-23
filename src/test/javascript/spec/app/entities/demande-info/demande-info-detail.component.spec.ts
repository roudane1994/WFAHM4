import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { DemandeInfoDetailComponent } from 'app/entities/demande-info/demande-info-detail.component';
import { DemandeInfo } from 'app/shared/model/demande-info.model';

describe('Component Tests', () => {
  describe('DemandeInfo Management Detail Component', () => {
    let comp: DemandeInfoDetailComponent;
    let fixture: ComponentFixture<DemandeInfoDetailComponent>;
    const route = ({ data: of({ demandeInfo: new DemandeInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [DemandeInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DemandeInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemandeInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load demandeInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demandeInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
