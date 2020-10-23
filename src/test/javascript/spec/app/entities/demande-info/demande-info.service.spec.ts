import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DemandeInfoService } from 'app/entities/demande-info/demande-info.service';
import { IDemandeInfo, DemandeInfo } from 'app/shared/model/demande-info.model';

describe('Service Tests', () => {
  describe('DemandeInfo Service', () => {
    let injector: TestBed;
    let service: DemandeInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemandeInfo;
    let expectedResult: IDemandeInfo | IDemandeInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DemandeInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DemandeInfo(0, 0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDemande: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DemandeInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDemande: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDemande: currentDate,
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.create(new DemandeInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DemandeInfo', () => {
        const returnedFromService = Object.assign(
          {
            nordre: 1,
            infoDemande: 'BBBBBB',
            reponse: 'BBBBBB',
            dateDemande: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDemande: currentDate,
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DemandeInfo', () => {
        const returnedFromService = Object.assign(
          {
            nordre: 1,
            infoDemande: 'BBBBBB',
            reponse: 'BBBBBB',
            dateDemande: currentDate.format(DATE_FORMAT),
            dateReponse: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDemande: currentDate,
            dateReponse: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DemandeInfo', () => {
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
