import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wfahm4TestModule } from '../../../test.module';
import { ObjetComponent } from 'app/entities/objet/objet.component';
import { ObjetService } from 'app/entities/objet/objet.service';
import { Objet } from 'app/shared/model/objet.model';

describe('Component Tests', () => {
  describe('Objet Management Component', () => {
    let comp: ObjetComponent;
    let fixture: ComponentFixture<ObjetComponent>;
    let service: ObjetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [ObjetComponent],
      })
        .overrideTemplate(ObjetComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObjetComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObjetService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Objet(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.objets && comp.objets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
