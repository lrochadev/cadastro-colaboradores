FROM node:9.11.2-slim as builder

COPY package*.json ./

RUN npm install npm@6.10.2 -g

RUN npm install

RUN mkdir /app && mv ./node_modules ./app

WORKDIR /app

COPY . .

RUN $(npm bin)/ng build --prod --output-path=dist

FROM nginx:1.16

COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /app/dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
