# Eteration Task

This app is an Android-based e-commerce application that provides product listing, product detail viewing, cart management, search, and filtering features. It allows users to browse products, add them to the cart, and quickly filter and search for specific items.

## Technologies

- Hilt
- Jetpack Compose
- Jetpack Compose Type-Safe Navigation
- Room
- Retrofit
- Coil
- Lottie

## Features

### 1. Product Listing
- Upon opening the app, products are fetched from the specified API and displayed on the screen.
- Products are displayed in groups of 4 at a time.
- If there are more than 4 products, **infinite scroll** is enabled, and more products are loaded as the user scrolls.

### 2. Product Details
- When a product is selected, the user is redirected to the screen displaying the product details.
- The product details screen includes an "Add to Cart" button, which adds the product to the cart.

### 3. Cart Management
- On the cart screen, users can increase or decrease the quantity of the products in the cart.
- The cart persists across app restarts, and products remain in the cart when the app is reopened.


### 4. Search
- The **"Search"** field on the home page allows users to search for products by name.
- As the user types, the product list dynamically updates.

### 5. Add to Cart from Product Detail
- Users can tap on any product to navigate to the **"Product Detail"** screen, where they can also add the product to the cart.

## Setup

### 1. Required Dependencies
- Open the project in Android Studio or another Kotlin-supported IDE.
- The project uses Hilt and other necessary dependencies, so ensure the required libraries are correctly added.

### 2. Database
- The app uses **Room** for local data storage.
- Necessary migration and schema files are included.

### 3. API
- Product data is fetched via Retrofit from the specified API.

### 4. Running the App
- Run the app and view the product listing screen.
- Test the features like filtering, searching, and cart management.

## Notes
- The app updates dynamically based on user interactions and optimizations.
