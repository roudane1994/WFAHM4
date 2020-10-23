import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { PieceJoindreUpdateComponent } from 'app/entities/piece-joindre/piece-joindre-update.component';
import { PieceJoindreService } from 'app/entities/piece-joindre/piece-joindre.service';
import { PieceJoindre } from 'app/shared/model/piece-joindre.model';

describe('Component Tests', () => {
  describe('PieceJoindre Management Update Component', () => {
    let comp: PieceJoindreUpdateComponent;
    let fixture: ComponentFixture<PieceJoindreUpdateComponent>;
    let service: PieceJoindreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [PieceJoindreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PieceJoindreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PieceJoindreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PieceJoindreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PieceJoindre(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PieceJoindre();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
