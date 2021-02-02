# ABS - Advertising banners service

## Run service
To run ABS you need:
1. Install [Docker Compose](https://docs.docker.com/compose/install/).
2. Clone this repository and move to it or download [docker-compose.yaml](https://raw.githubusercontent.com/tigrulya-exe/AdvertisingBanners/master/docker-compose.yaml) with curl:
   ```bash
    curl https://raw.githubusercontent.com/tigrulya-exe/AdvertisingBanners/master/docker-compose.yaml > docker-compose.yaml
    ```
3. Run 
    ```bash
    docker-compose pull && docker-compose up 
    ```
    or 
    ```bash
    docker-compose pull && docker-compose up -d
    ```
    to run service in detached mode.
4. Open http://localhost:80 
## Stop service
To stop UBS run following command in directory, containing docker-compose.yaml
```bash
docker-compose down
```
