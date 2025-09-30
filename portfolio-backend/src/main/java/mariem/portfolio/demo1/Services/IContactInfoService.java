package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.ContactInfo;

public interface IContactInfoService {
    ContactInfo get();
    ContactInfo update(ContactInfo info);
}
