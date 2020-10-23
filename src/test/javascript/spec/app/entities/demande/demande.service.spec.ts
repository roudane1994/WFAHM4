import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DemandeService } from 'app/entities/demande/demande.service';
import { IDemande, Demande } from 'app/shared/model/demande.model';

describe('Service Tests', () => {
  describe('Demande Service', () => {
    let injector: TestBed;
    let service: DemandeService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemande;
    let expectedResult: IDemande | IDemande[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DemandeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Demande(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateBesion: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            dateAffictation: currentDate.format(DATE_FORMAT),
            dateClouture: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Demande', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateBesion: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            dateAffictation: currentDate.format(DATE_FORMAT),
            dateClouture: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBesion: currentDate,
            dateCreation: currentDate,
            dateAffictation: currentDate,
            dateClouture: currentDate,
          },
          returnedFromService
        );

        service.create(new Demande()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Demande', () => {
        const returnedFromService = Object.assign(
          {
            numero: 1,
            rtr: 'BBBBBB',
            rtrLibelle: 'BBBBBB',
            objet: 'BBBBBB',
            description: 'BBBBBB',
            dateBesion: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            createur: 'BBBBBB',
            budget: 1,
            affectationLibelle: 'BBBBBB',
            dateAffictation: currentDate.format(DATE_FORMAT),
            moreInformation: 'BBBBBB',
            etat: 'BBBBBB',
            messageValidation: 'BBBBBB',
            messageClouture: 'BBBBBB',
            dateClouture: currentDate.format(DATE_FORMAT),
            meilleurPrixMagasin: 1,
            prixNegocie: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBesion: currentDate,
            dateCreation: currentDate,
            dateAffictation: currentDate,
            dateClouture: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Demande', () => {
        const returnedFromService = Object.assign(
          {
            numero: 1,
            rtr: 'BBBBBB',
            rtrLibelle: 'BBBBBB',
            objet: 'BBBBBB',
            description: 'BBBBBB',
            dateBesion: currentDate.format(DATE_FORMAT),
            dateCreation: currentDate.format(DATE_FORMAT),
            createur: 'BBBBBB',
            budget: 1,
            affectationLibelle: 'BBBBBB',
            dateAffictation: currentDate.format(DATE_FORMAT),
            moreInformation: 'BBBBBB',
            etat: 'BBBBBB',
            messageValidation: 'BBBBBB',
            messageClouture: 'BBBBBB',
            dateClouture: currentDate.format(DATE_FORMAT),
            meilleurPrixMagasin: 1,
            prixNegocie: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBesion: currentDate,
            dateCreation: currentDate,
            dateAffictation: currentDate,
            dateClouture: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Demande', () => {
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
