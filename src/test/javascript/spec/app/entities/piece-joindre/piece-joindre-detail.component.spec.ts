import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { PieceJoindreDetailComponent } from 'app/entities/piece-joindre/piece-joindre-detail.component';
import { PieceJoindre } from 'app/shared/model/piece-joindre.model';

describe('Component Tests', () => {
  describe('PieceJoindre Management Detail Component', () => {
    let comp: PieceJoindreDetailComponent;
    let fixture: ComponentFixture<PieceJoindreDetailComponent>;
    const route = ({ data: of({ pieceJoindre: new PieceJoindre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [PieceJoindreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PieceJoindreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PieceJoindreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pieceJoindre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pieceJoindre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
