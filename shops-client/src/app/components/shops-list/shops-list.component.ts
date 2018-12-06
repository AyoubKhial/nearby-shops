import { Component, OnInit, OnDestroy } from '@angular/core';
import { ShopsService } from 'src/app/services/shops.service';
import { Shop } from '../../models/shop';
import { Subject } from 'rxjs';
import { takeUntil } from "rxjs/operators";

@Component({
    selector: 'app-shops-list',
    templateUrl: './shops-list.component.html',
    styleUrls: ['./shops-list.component.css']
})
export class ShopsListComponent implements OnInit, OnDestroy {

    public shops: Shop[] = [];
    private unsubscribe = new Subject<void>();

    constructor(private shopsService: ShopsService) { }

    ngOnInit() {
       this.listShopsSortedByDistance();
    }

    listShopsSortedByDistance() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.shopsService.getAllShops(position.coords).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
                    this.shops = response.content;
                });
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    // unsubscribing from observable subscriptions 
    ngOnDestroy(): void {
        this.unsubscribe.next();
        this.unsubscribe.complete();
    }
}
