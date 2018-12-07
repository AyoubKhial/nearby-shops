# nearby-shops
United Remote's coding challenge - nearby shops app

# Get started

**Clone the repo**
```
git clone https://github.com/AyoubKhial/nearby-shops.git
cd shops-client
```

**Install npm packages** 

Install the npm packages described in the package.json and verify that it works:
```
npm install
```

### For the testing purpose, you can use the following user:
email: **ayoub.khial@gmail.com**
password: **123456789**

# Resource components

## User API resources

| resources | method | description |
| --------- |--------| ----------- |
| ```/users/signup``` | POST | create a new user passing email and password in the body. |
| ```/users/signin``` | POST | login by passing email and password in the body.|

## Shop API resources

| resources | method | description |
| ------------- |--------| ----- |
| ```/shops?longitude={}&latitude={}``` | GET | returns a list of shops sorted by the nearest to your curent location. |
| ```/shops/liked``` | GET | returns a list of the preferred shops of the current user. |
| ```/shops/disliked``` | GET | returns a list of the disliked shops of the current user. |
| ```/shops/liked?shop={shopId}``` | POST | add a specific shop into the preferred shops of current user. |
| ```/shops/disliked?shop={shopId}``` | POST | add a specific shop into the disliked shops of current user. |
| ```/shops/like/undo?shop={shopId}``` | DELETE | remove a shop from preferred shops. |
| ```/shops/dislike/undo?shop={shopId}``` | DELETE | remove a shop from disliked shops. |

All the GET request have two more parameters :
- ```page```: The current page of the shops list.
- ```size```: The size of each page.