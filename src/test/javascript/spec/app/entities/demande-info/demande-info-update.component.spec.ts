import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { DemandeInfoUpdateComponent } from 'app/entities/demande-info/demande-info-update.component';
import { DemandeInfoService } from 'app/entities/demande-info/demande-info.service';
import { DemandeInfo } from 'app/shared/model/demande-info.model';

describe('Component Tests', () => {
  describe('DemandeInfo Management Update Component', () => {
    let comp: DemandeInfoUpdateComponent;
    let fixture: ComponentFixture<DemandeInfoUpdateComponent>;
    let service: DemandeInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [DemandeInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DemandeInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandeInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandeInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DemandeInfo(123);
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
        const entity = new DemandeInfo();
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
