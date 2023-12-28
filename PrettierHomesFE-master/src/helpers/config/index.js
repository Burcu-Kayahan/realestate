export const config = {
  project: {
    name: "Prettier Homes",
    slogan: "Unlocking Your Home's Potential, Together",
    description:
      "Our slogan, 'Unlocking Your Home s Potential, Together, reflects our dedication  to helping clients realize the full potential  of their real estate investments through collaborative and personalized services",
    version: "1.0.0",
  },
  contact: {
    phone: "+1-123-456-456-65",
    email: "info@realestate.com",
    address: "329 Queensberry Street, North Melbourne VIC 3051, Australia",
    website: "https://realestate.com",
    mapURL: "https://goo.gl/maps/aekRiJbXVYuqVMxp7",
    mapEmbedURL:
      "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1571543.3278485276!2d31.026053301554516!3d39.70773029069568!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14d34ef01d406c31%3A0x5669332ebe02ad84!2sAnkara!5e0!3m2!1sfr!2str!4v1701539114535!5m2!1sfr!2str",
    socialMedia: {
      twitter: "https://twitter.com",
      facebook: "https://facebook.com",
      instagram: "https://instagram.com",
      linkedin: "https://linkedin.com",
      youtube: "https://youtube.com",
    },
  },


  api: {
    baseUrl: process.env.REACT_APP_BASE_URL
    // baseUrl: "https://prettiere-homes-4e91b85aa8ef.herokuapp.com",
  },
  
  pageRoles: {
    dashboard: ["ADMIN", "MANAGER", "CUSTOMER"],
    adverts: ["ADMIN", "MANAGER", "CUSTOMER"],
    customer: ["ADMIN", "MANAGER"],
    advertType: ["ADMIN", "MANAGER"],
    categories: ["ADMIN", "MANAGER"],
    tourRequests: ["ADMIN", "MANAGER"],
    contactMessages: ["ADMIN", "MANAGER", "CUSTOMER"],
    reports: ["ADMIN", "MANAGER", "CUSTOMER"],
  },
};
