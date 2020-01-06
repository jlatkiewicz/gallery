import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Image} from "./image";


@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private url = 'http://localhost:8080/images';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
    responseType: 'text'
  };

  constructor(private http: HttpClient) {
  }

  getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(this.url);
  }

  addImage(imageLink: string): Observable<string> {
    return this.http.post<string>(this.url, {"imageLink": imageLink}, this.httpOptions);
  }

  deleteImage(imageId: string): Observable<string> {
    const url = `${this.url}/${imageId}`;
    return this.http.delete<string>(url, this.httpOptions);
  }
}
