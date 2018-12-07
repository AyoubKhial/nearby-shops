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

    ngOnInit(): void {
        this.listShopsSortedByDistance();
    }

    // get all shops sorted by distance and remove the preferred and disliked ones
    listShopsSortedByDistance(): void {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.shopsService.getAllShops(position.coords).pipe(takeUntil(this.unsubscribe)).subscribe(allShops => {
                    this.shopsService.getPreferredShops().pipe(takeUntil(this.unsubscribe)).subscribe(likedShops => {
                        this.shopsService.getDislikedShops().pipe(takeUntil(this.unsubscribe)).subscribe(dislikedShops => {
                            var shopsToRemoveFromList = likedShops.content.concat(dislikedShops.content);
                            this.shops = allShops.content;
                            for (var i = this.shops.length - 1; i >= 0; i--) {
                                for (var j = 0; j < shopsToRemoveFromList.length; j++) {
                                    if (this.shops[i] && (this.shops[i].name === shopsToRemoveFromList[j].name)) {
                                        this.shops.splice(i, 1);
                                    }
                                }
                            }
                        })
                    })
                });
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    // add a shop to the preferred list
    addShopToPreferred(shopId: string): void {
        this.shopsService.addShopToPreferred(shopId).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            if (response.code == 201) {
                this.listShopsSortedByDistance();
            }
        });
    }

    // add a shop to the disliked list
    addShopToDisliked(shopId: string): void {
        this.shopsService.addShopToDisliked(shopId).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            if (response.code == 201) {
                this.listShopsSortedByDistance();
            }
        });
    }

    // unsubscribing from observable subscriptions 
    ngOnDestroy(): void {
        this.unsubscribe.next();
        this.unsubscribe.complete();
    }
}
