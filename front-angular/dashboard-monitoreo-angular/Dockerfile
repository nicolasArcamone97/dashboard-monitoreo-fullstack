# Etapa 1: Construcción de la aplicación Angular
FROM node:18-alpine AS build

WORKDIR /usr/src/app

COPY package*.json ./
RUN npm install

COPY . .

# Construye la aplicación Angular en modo producción
RUN npm run build --prod

# Etapa 2: Servidor Nginx para servir la aplicación
FROM nginx:alpine

# Copia el archivo de configuración personalizado para Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia los archivos generados por Angular desde la etapa 'build'
COPY --from=build /usr/src/app/dist/dashboard-monitoreo-angular/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
