import { login } from '@/Redux/Auth/Action'
import { Button } from '@/components/ui/button'
import { Form, FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import React from 'react'
import { useForm } from 'react-hook-form'
import { useDispatch } from 'react-redux'

const Login = () => {

  const dispatch=useDispatch()

    
        const form=useForm({
            // resolver
            defaultValues:{
                email:"",
                password:"",
               
                
            }
        })
    
        const onSubmit=(data)=>{
          dispatch(login(data))
            console.log("login success", data)
        }
  return (
    <div>
       
          <h2 className=' mb-3 text-lg text-center'>Login</h2>
        <Form {...form}>
              <form className="space-y-3"onSubmit={form.handleSubmit(onSubmit)}>
                
                <FormField
                name="email"
                render={ ({field})=>
                (
                  <FormItem>
                  <FormControl>
                    <Input {...field} type="text" className="border w-full border-gray-700 py-5 px-5" placeholder="email"/>
                  </FormControl>
                  <FormMessage/>
                </FormItem>
                )}/>
                <FormField
                name="password"
                render={ ({field})=>
                (
                  <FormItem>
                  <FormControl>
                    <Input {...field} type="text" className="border w-full border-gray-700 py-5 px-5" placeholder="password"/>
                  </FormControl>
                  <FormMessage/>
                </FormItem>
                )}/>
                
                
                
                  
                  <Button type="submit" className="w-full mt-5">login</Button>

                  
                
              </form>
         </Form>



    </div>
  )
}

export default Login