import { FilterByCustomerNamePipe } from './filter-by-customer-name.pipe';

describe('FilterByCustomerNamePipe', () => {
  it('create an instance', () => {
    const pipe = new FilterByCustomerNamePipe();
    expect(pipe).toBeTruthy();
  });
});
