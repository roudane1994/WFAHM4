import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wfahm4TestModule } from '../../../test.module';
import { PieceJoindreComponent } from 'app/entities/piece-joindre/piece-joindre.component';
import { PieceJoindreService } from 'app/entities/piece-joindre/piece-joindre.service';
import { PieceJoindre } from 'app/shared/model/piece-joindre.model';

describe('Component Tests', () => {
  describe('PieceJoindre Management Component', () => {
    let comp: PieceJoindreComponent;
    let fixture: ComponentFixture<PieceJoindreComponent>;
    let service: PieceJoindreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [PieceJoindreComponent],
      })
        .overrideTemplate(PieceJoindreComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PieceJoindreComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PieceJoindreService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PieceJoindre(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pieceJoindres && comp.pieceJoindres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
