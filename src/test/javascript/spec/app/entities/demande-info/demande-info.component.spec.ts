import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wfahm4TestModule } from '../../../test.module';
import { DemandeInfoComponent } from 'app/entities/demande-info/demande-info.component';
import { DemandeInfoService } from 'app/entities/demande-info/demande-info.service';
import { DemandeInfo } from 'app/shared/model/demande-info.model';

describe('Component Tests', () => {
  describe('DemandeInfo Management Component', () => {
    let comp: DemandeInfoComponent;
    let fixture: ComponentFixture<DemandeInfoComponent>;
    let service: DemandeInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [DemandeInfoComponent],
      })
        .overrideTemplate(DemandeInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandeInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandeInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DemandeInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.demandeInfos && comp.demandeInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
