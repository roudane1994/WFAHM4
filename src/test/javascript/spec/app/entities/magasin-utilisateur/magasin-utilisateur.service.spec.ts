import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MagasinUtilisateurService } from 'app/entities/magasin-utilisateur/magasin-utilisateur.service';
import { IMagasinUtilisateur, MagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

describe('Service Tests', () => {
  describe('MagasinUtilisateur Service', () => {
    let injector: TestBed;
    let service: MagasinUtilisateurService;
    let httpMock: HttpTestingController;
    let elemDefault: IMagasinUtilisateur;
    let expectedResult: IMagasinUtilisateur | IMagasinUtilisateur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MagasinUtilisateurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MagasinUtilisateur(0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MagasinUtilisateur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MagasinUtilisateur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MagasinUtilisateur', () => {
        const returnedFromService = Object.assign(
          {
            nordre: 1,
            utilisateur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MagasinUtilisateur', () => {
        const returnedFromService = Object.assign(
          {
            nordre: 1,
            utilisateur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MagasinUtilisateur', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
