// Dans un fichier api/property-service.js ou où vous gérez vos appels API

export const getPropertyDetails = async (propertyId) => {
  try {
    const response = await fetch(`/api/properties/${propertyId}`); // Remplacez cette URL par l'URL réelle de votre API
    const propertyDetails = await response.json();
    return propertyDetails;
  } catch (error) {
    console.error(
      "Erreur lors de la récupération des détails de propriété",
      error
    );
    throw error;
  }
};
