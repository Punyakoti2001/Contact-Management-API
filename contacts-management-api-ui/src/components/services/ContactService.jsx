import axios from 'axios';

class ContactService {

  static fetchContacts()
  {
    const BASE_URL = "http://localhost:9091/build-bot/v1/contact/fetch-all-contacts";
try{
  let response = axios.get(BASE_URL)
  return response

}
catch(error)
{
  throw error
}
    


  }

    static  addContact(contact) {
        const BASE_URL = "http://localhost:9091/build-bot/v1/contact/create-contact";
    
        try {
          const response =  axios.post(BASE_URL, contact);
          return response
        } catch (error) {
          throw error; 
        }
      }

      static deleteContact(contactId)
      {
        const BASE_URL = `http://localhost:9091/build-bot/v1/contact/delete-contact?contactId=${contactId}`;
        
      try {
          const response = axios.delete(BASE_URL);
          return response;
      } catch (error) {
    
          throw error; 
      }
      }

      static getContact(contactId)
      {
        
      }
      static updateContact(contact)
      {
        
      }
}

export default ContactService;
