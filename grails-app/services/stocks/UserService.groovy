package stocks

import jxl.Sheet
import jxl.Workbook
import org.bouncycastle.crypto.CipherKeyGenerator

class UserService {

    List<Map> importUser(String filePath) {

        def result = []
        def file = new File(filePath)
        Workbook book = Workbook.getWorkbook(file)
        Sheet sheet = book.getSheet(0)
        for (def i = 1; i < sheet.rows; i++) {
            def brokerId = sheet.getCell(7, i)?.contents?.trim()
            def model = [
                    firstName   : sheet.getCell(0, i)?.contents?.trim(),
                    lastName    : sheet.getCell(1, i)?.contents?.trim(),
                    mobile      : sheet.getCell(2, i)?.contents?.trim(),
                    sex         : FarsiNormalizationFilter.apply(sheet.getCell(3, i)?.contents?.trim()) == FarsiNormalizationFilter.apply("مرد") ? 'male' : 'female',
                    nationalCode: sheet.getCell(4, i)?.contents?.trim(),
                    city        : City.findByName(FarsiNormalizationFilter.apply(sheet.getCell(5, i)?.contents?.trim())) ?: City.get(390657956l),
                    email       : sheet.getCell(6, i)?.contents?.trim(),
                    broker      : brokerId && brokerId != '' ? Broker.get(brokerId?.toLong()) : null
            ]
            if(model.nationalCode?.length() != 10)
                model.nationalCode = null

            if (!User.findByUsername(model.email)) {
                def user = new User(
                        firstName: model.firstName,
                        lastName: model.lastName,
                        mobile: model.mobile,
                        sex: model.sex,
                        nationalCode: model.nationalCode,
                        city: model.city,
                        email: model.email,
                        username: model.email,
                        broker: model.broker,
                        password: !model.mobile || model.mobile == '' ? '1234567890' : model.mobile
                )
                if(!user.save(flush: true)){
                    model.errors = user?.errors?.allErrors
                }
            } else {
                model.errors = ["نام کاربری تکراری است."]
            }
            result << model
        }
        result
    }
}
