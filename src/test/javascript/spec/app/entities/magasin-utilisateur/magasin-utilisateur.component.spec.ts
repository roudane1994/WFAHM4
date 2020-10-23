import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wfahm4TestModule } from '../../../test.module';
import { MagasinUtilisateurComponent } from 'app/entities/magasin-utilisateur/magasin-utilisateur.component';
import { MagasinUtilisateurService } from 'app/entities/magasin-utilisateur/magasin-utilisateur.service';
import { MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

describe('Component Tests', () => {
  describe('MagasinUtilisateur Management Component', () => {
    let comp: MagasinUtilisateurComponent;
    let fixture: ComponentFixture<MagasinUtilisateurComponent>;
    let service: MagasinUtilisateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [MagasinUtilisateurComponent],
      })
        .overrideTemplate(MagasinUtilisateurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MagasinUtilisateurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MagasinUtilisateurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MagasinUtilisateur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.magasinUtilisateurs && comp.magasinUtilisateurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
