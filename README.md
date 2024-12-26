https://redis.io/docs/latest/operate/oss_and_stack/install/install-stack/docker/
도커 (Docker) 프로그램을 사용한 Redis 

cmd 창에 명령어 입력 
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
