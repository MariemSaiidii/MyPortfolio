# Use a lightweight nginx image to serve the Angular build output
FROM nginx:alpine

# Remove default nginx static content
RUN rm -rf /usr/share/nginx/html/*

# Copy Angular build output to nginx html folder
COPY dist/portfolio-frontend/ /usr/share/nginx/html/

# Copy custom nginx configuration to replace the default one
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start nginx server
CMD ["nginx", "-g", "daemon off;"]
