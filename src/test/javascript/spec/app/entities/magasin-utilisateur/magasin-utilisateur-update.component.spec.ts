import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { MagasinUtilisateurUpdateComponent } from 'app/entities/magasin-utilisateur/magasin-utilisateur-update.component';
import { MagasinUtilisateurService } from 'app/entities/magasin-utilisateur/magasin-utilisateur.service';
import { MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

describe('Component Tests', () => {
  describe('MagasinUtilisateur Management Update Component', () => {
    let comp: MagasinUtilisateurUpdateComponent;
    let fixture: ComponentFixture<MagasinUtilisateurUpdateComponent>;
    let service: MagasinUtilisateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [MagasinUtilisateurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MagasinUtilisateurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MagasinUtilisateurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MagasinUtilisateurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MagasinUtilisateur(123);
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
        const entity = new MagasinUtilisateur();
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
