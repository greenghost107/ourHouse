import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    helper:JwtHelperService;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
        this.helper = new JwtHelperService();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username, password) {
        return this.http.post<any>(`http://localhost:8080/authenticate`, { username, password })
            .pipe(map(data => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                const decoded = this.helper.decodeToken(data.token);

                let user = new User(decoded.sub);
                
                localStorage.setItem('currentUser', JSON.stringify(user));
                localStorage.setItem('access_token',data.token);
                
                this.currentUserSubject.next(user);
                
                return user;
            }));
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        localStorage.removeItem('access_token');
        this.currentUserSubject.next(null);
    }
    
    getCurrentUser()
    {
        return this.currentUser;
    }

    isTokenExpired(){
        const token = 'Bearer ' +localStorage.getItem("access_token");
        return this.helper.isTokenExpired(token);
    }
}