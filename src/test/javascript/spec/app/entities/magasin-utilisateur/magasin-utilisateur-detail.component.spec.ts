import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wfahm4TestModule } from '../../../test.module';
import { MagasinUtilisateurDetailComponent } from 'app/entities/magasin-utilisateur/magasin-utilisateur-detail.component';
import { MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

describe('Component Tests', () => {
  describe('MagasinUtilisateur Management Detail Component', () => {
    let comp: MagasinUtilisateurDetailComponent;
    let fixture: ComponentFixture<MagasinUtilisateurDetailComponent>;
    const route = ({ data: of({ magasinUtilisateur: new MagasinUtilisateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Wfahm4TestModule],
        declarations: [MagasinUtilisateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MagasinUtilisateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MagasinUtilisateurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load magasinUtilisateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.magasinUtilisateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
