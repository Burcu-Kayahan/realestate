# Use an official Node.js runtime as a parent image
FROM node:20.10

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the local code to the container
COPY . .

# Expose the port on which the app will run (change it if your app uses a different port)
EXPOSE 3000

# Define the command to start your React app
CMD ["npm", "start"]


#Bu Dockerfile, sadece geliştirme modunda çalışan bir React uygulamasını başlatmayı amaçlar. Derleme ve optimize etme işlemleri yoktur, bu nedenle daha hızlı bir geliştirme süreci sağlar.